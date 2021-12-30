<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="申请名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入申请名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="批次" prop="batchId">
        <el-select v-model="queryParams.batchId" placeholder="请选择批次" clearable size="small">
          <el-option
            v-for="item in batchList"
            :key="item.id"
            :label="item.number"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="申请状态" prop="applyStatus">
        <el-select v-model="queryParams.applyStatus" placeholder="请选择申请状态" clearable size="small">
          <el-option
            v-for="dict in applyStatusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
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
          v-hasPermi="['ewem:apply:add']"
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
          v-hasPermi="['ewem:apply:edit']"
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
          v-hasPermi="['ewem:apply:remove']"
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
          v-hasPermi="['ewem:apply:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="applyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="申请状态" align="center" prop="id" v-if="false"/>
      <el-table-column label="批次号" align="center" prop="batch.number"/>
      <el-table-column label="申请名称" align="center" prop="name"/>
      <el-table-column label="申请数量" align="center" prop="quantity"/>
      <el-table-column label="防伪码长度" align="center" prop="antiLength"/>
      <el-table-column label="申请状态" align="center" prop="applyStatus">
        <template slot-scope="scope">
          <dict-tag :options="applyStatusOptions" :value="scope.row.applyStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="statusOptions" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ewem:apply:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ewem:apply:remove']"
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

    <!-- 添加或修改码申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="90px">
        <el-form-item label="选择批次" prop="batchId">
          <el-select v-model="form.batchId">
            <el-option
              v-for="item in batchList"
              :key="item.id"
              :label="item.number"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="申请名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入申请名称"/>
        </el-form-item>
        <el-form-item label="申请数量" prop="quantity">
          <el-input v-model="form.quantity"
                    oninput="value=value.replace(/[^\d]/g,'')"
                    maxlength="3"
                    placeholder="请输入申请数量"/>
        </el-form-item>
        <el-form-item label="生成防伪码" prop="useAnti">
          <el-switch v-model="form.useAnti"/>
        </el-form-item>
        <div v-show="form.useAnti">
          <el-form-item label="防伪码长度" prop="antiLength">
            <el-input-number v-model="form.antiLength" :min="6" :max="12" placeholder="请输入防伪码长度"/>
          </el-form-item>
        </div>
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
  import { addApply, delApply, exportApply, getApply, listApply, updateApply } from '@/api/ewem/apply'
  import { listBatch } from '@/api/ewem/batch'

  export default {
    name: 'Apply',
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
        // 码申请表格数据
        applyList: [],
        // 弹出层标题
        title: '',
        // 是否显示弹出层
        open: false,
        // 批次字典
        batchList: [],
        // 申请状态字典
        applyStatusOptions: [],
        // 状态字典
        statusOptions: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          name: undefined,
          applyStatus: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          name: [
            { required: true, message: '申请名称不能为空', trigger: 'blur' }
          ],
          quantity: [
            { required: true, message: '申请数量不能为空', trigger: 'blur' }
          ],
          batchId: [
            { required: true, message: '批次不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    created() {
      this.getList()
      this.getDicts('ewem_apply_status').then(response => {
        this.applyStatusOptions = response.data
      })
      this.getDicts('sys_normal_disable').then(response => {
        this.statusOptions = response.data
      })
      this.handleBatch()
    },
    methods: {
      /** 查询码申请列表 */
      getList() {
        this.loading = true
        listApply(this.queryParams).then(response => {
          this.applyList = response.rows
          this.total = response.total
          this.loading = false
        })
      },
      handleBatch() {
        listBatch().then(response => {
          this.batchList = response.rows
        })
      },
      // 申请状态字典翻译
      applyStatusFormat(row, column) {
        return this.selectDictLabel(this.applyStatusOptions, row.applyStatus)
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
          name: undefined,
          quantity: undefined,
          applyStatus: undefined,
          batchId: undefined,
          status: '0',
          delFlag: undefined,
          useAnti: undefined,
          antiLength: undefined,
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
      /** 新增按钮操作 */
      handleAdd() {
        this.reset()
        this.open = true
        this.title = '添加码申请'
        this.form.antiLength = 6
        this.form.useAnti = false
      },

      /** 修改按钮操作 */
      handleUpdate(row) {
        this.loading = true
        this.reset()
        const id = row.id || this.ids
        getApply(id).then(response => {
          this.loading = false
          this.form = response.data
          this.open = true
          this.title = '修改码申请'
        })
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs['form'].validate(valid => {
          if (valid) {
            this.buttonLoading = true
            if (!this.form.useAnti){
              this.form.antiLength = 0
            }
            if (this.form.id != null) {
              updateApply(this.form).then(response => {
                this.msgSuccess('修改成功')
                this.open = false
                this.getList()
              }).finally(() => {
                this.buttonLoading = false
              })
            } else {
              addApply(this.form).then(response => {
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
        this.$confirm('是否确认删除码申请编号为"' + ids + '"的数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.loading = true
          return delApply(ids)
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
        const queryParams = this.queryParams
        this.$confirm('是否确认导出所有码申请数据项?', '警告', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.exportLoading = true
          return exportApply(queryParams)
        }).then(response => {
          this.download(response.msg)
          this.exportLoading = false
        }).catch(() => {
        })
      }
    }
  }
</script>
