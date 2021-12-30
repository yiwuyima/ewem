import request from '@/utils/request'

// 查询产品分类列表
export function listCategory(query) {
  return request({
    url: '/ewem/category/list',
    method: 'get',
    params: query
  })
}

// 查询产品分类详细
export function getCategory(id) {
  return request({
    url: '/ewem/category/' + id,
    method: 'get'
  })
}

// 新增产品分类
export function addCategory(data) {
  return request({
    url: '/ewem/category',
    method: 'post',
    data: data
  })
}

// 修改产品分类
export function updateCategory(data) {
  return request({
    url: '/ewem/category',
    method: 'put',
    data: data
  })
}

// 删除产品分类
export function delCategory(id) {
  return request({
    url: '/ewem/category/' + id,
    method: 'delete'
  })
}

// 导出产品管理
export function exportCategory(query) {
  return request({
    url: '/ewem/category/export',
    method: 'get',
    params: query
  })
}
