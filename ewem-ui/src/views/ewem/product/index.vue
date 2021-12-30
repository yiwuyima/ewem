<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="产品编码" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入产品编码"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入产品名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="产品分类" prop="categoryId">
        <el-select v-model="queryParams.categoryId" placeholder="请选择分类" clearable size="small">
          <el-option
            v-for="item in categoryList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['ewem:product:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['ewem:product:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['ewem:product:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          :loading="exportLoading"
          @click="handleExport"
          v-hasPermi="['ewem:product:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="productList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="产品名称" align="center" prop="id" v-if="false"/>
      <el-table-column label="产品编码" align="center" prop="code"/>
      <el-table-column label="产品分类" align="center" prop="category.name"/>
      <el-table-column label="产品名称" align="center" prop="name"/>
      <el-table-column label="产品条码" align="center" prop="barCode"/>
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat"/>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ewem:product:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ewem:product:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改产品管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="分类" prop="categoryId">
          <el-select v-model="form.categoryId">
            <el-option
              v-for="item in categoryList"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="产品编码" prop="code">
          <el-input v-model="form.code" placeholder="请输入产品编码"/>
        </el-form-item>
        <el-form-item label="产品名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入产品名称"/>
        </el-form-item>
        <el-form-item label="产品条码" prop="barCode">
          <el-input v-model="form.barCode" placeholder="请输入产品条码"/>
        </el-form-item>

        <!-- 动态增加属性 -->
        <el-row>
          <div v-for="(item, index) in form.attrs" :key="index">
            <el-col :span="10">
              <el-form-item
                label="属性"
                :prop="'attrs.' + index + '.k'">
                <el-input v-model="item.k"/>
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-form-item label="值"
                            :prop="'attrs.' + index + '.v'">
                <el-input v-model="item.v"/>
              </el-form-item>
            </el-col>
            <el-col :span="3">
              <el-form-item>
                <i class="el-icon-delete" @click="deleteItem(item, index)"/>
              </el-form-item>
            </el-col>
          </div>
        </el-row>
        <el-button @click="addItem" type="primary">增加属性</el-button>
        <br>
        <br>
        <br>
        <el-form-item label="富文本描述" >
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="产品图片">
          <imageUpload v-model="form.img"/>
        </el-form-item>
        <el-form-item label="产品视频">
          <fileUpload v-model="form.video"/>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listProduct, getProduct, delProduct, addProduct, updateProduct, exportProduct } from '@/api/ewem/product'
  import { listCategory} from "@/api/ewem/category";

  export default {
    name: 'Product',
    data() {
      return {
        // 按钮loading
        buttonLoading: false,
        // 遮罩层
        loading: true,
        // 导出遮罩层
        exportLoading: false,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 产品管理表格数据
        productList: [],
        // 分类列表
        categoryList:[],
        // 弹出层标题
        title: '',
        // 是否显示弹出层
        open: false,
        // 状态字典
        statusOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          code: undefined,
          name: undefined
        },
        // 表单参数
        form: {
          k: '',
          v: '',
          attrs: []
        },
        // 表单校验
        rules: {
          code: [
            { required: true, message: '产品编码不能为空', trigger: 'blur' }
          ],
          name: [
            { required: true, message: '产品名称不能为空', trigger: 'blur' }
          ],
          barCode: [
            { required: true, message: '产品条码不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    created() {
      this.getList()
      this.getDicts('sys_normal_disable').then(response => {
        this.statusOptions = response.data
      })
      this.handleCategory()
    },
    methods: {
      /** 查询产品管理列表 */
      getList() {
        this.loading = true
        listProduct(this.queryParams).then(response => {
          this.productList = response.rows
          this.total = response.total
          this.loading = false
        })
      },
      addItem() {
        this.form.attrs.push({
          k: '',
          v: ''
        })
      },
      deleteItem(item, index) {
        this.form.attrs.splice(index, 1)
      },
      // 状态字典翻译
      statusFormat(row, column) {
        return this.selectDictLabel(this.statusOptions, row.status)
      },
      // 取消按钮
      cancel() {
        this.open = false
        this.reset()
      },
      // 表单重置
      reset() {
        this.form = {
          id: undefined,
          categoryId: undefined,
          code: undefined,
          name: undefined,
          barCode: undefined,
          attrs: [],
          content: undefined,
          img: undefined,
          video: undefined,
          status: '0',
          delFlag: undefined,
          createBy: undefined,
          createTime: undefined,
          updateBy: undefined,
          updateTime: undefined,
          remark: undefined
        }
        this.resetForm('form')
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1
        this.getList()
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm('queryForm')
        this.handleQuery()
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      handleCategory(){
        listCategory().then(response => {
          this.categoryList = response.rows;
        });
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset()
        this.open = true
        this.title = '添加产品管理'
        this.handleCategory()
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.loading = true
        this.reset()
        const id = row.id || this.ids
        getProduct(id).then(response => {
          this.loading = false
          this.form = response.data
          this.open = true
          this.title = '修改产品管理'
        })
        this.handleCategory()
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs['form'].validate(valid => {
          if (valid) {
            this.buttonLoading = true
            if (this.form.id != null) {
              updateProduct(this.form).then(response => {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              }).finally(() => {
                this.buttonLoading = false
              })
            } else {
              addProduct(this.form).then(response => {
                this.msgSuccess('新增成功')
                this.open = false
                this.getList()
              }).finally(() => {
                this.buttonLoading = false
              })
            }
          }
        })
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids
        this.$confirm('是否确认删除产品管理编号为"' + ids + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.loading = true
          return delProduct(ids)
        }).then(() => {
          this.loading = false
          this.getList()
          this.msgSuccess('删除成功')
        }).finally(() => {
          this.loading = false
        })
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有产品数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.exportLoading = true;
          return exportProduct(queryParams);
        }).then(response => {
          this.download(response.msg);
          this.exportLoading = false;
        }).catch(() => {});
      }
    }
  }
</script>
