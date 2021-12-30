import request from '@/utils/request'

// 查询码申请列表
export function listApply(query) {
  return request({
    url: '/ewem/apply/list',
    method: 'get',
    params: query
  })
}

// 查询码申请详细
export function getApply(id) {
  return request({
    url: '/ewem/apply/' + id,
    method: 'get'
  })
}

// 新增码申请
export function addApply(data) {
  return request({
    url: '/ewem/apply',
    method: 'post',
    data: data
  })
}

// 修改码申请
export function updateApply(data) {
  return request({
    url: '/ewem/apply',
    method: 'put',
    data: data
  })
}

// 删除码申请
export function delApply(id) {
  return request({
    url: '/ewem/apply/' + id,
    method: 'delete'
  })
}

// 导出码申请
export function exportApply(query) {
  return request({
    url: '/ewem/apply/export',
    method: 'get',
    params: query
  })
}