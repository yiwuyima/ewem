import request from '@/utils/request'

// 查询码管理列表
export function listCode(query) {
  return request({
    url: '/ewem/code/list',
    method: 'get',
    params: query
  })
}

// 查询码管理详细
export function getCode(id) {
  return request({
    url: '/ewem/code/' + id,
    method: 'get'
  })
}

// 新增码管理
export function addCode(data) {
  return request({
    url: '/ewem/code',
    method: 'post',
    data: data
  })
}

// 修改码管理
export function updateCode(data) {
  return request({
    url: '/ewem/code',
    method: 'put',
    data: data
  })
}

// 删除码管理
export function delCode(id) {
  return request({
    url: '/ewem/code/' + id,
    method: 'delete'
  })
}

// 导出码管理
export function exportCode(query) {
  return request({
    url: '/ewem/code/export',
    method: 'get',
    params: query
  })
}
