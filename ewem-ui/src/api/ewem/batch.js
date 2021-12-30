import request from '@/utils/request'

// 查询批次列表
export function listBatch(query) {
  return request({
    url: '/ewem/batch/list',
    method: 'get',
    params: query
  })
}

// 查询批次详细
export function getBatch(id) {
  return request({
    url: '/ewem/batch/' + id,
    method: 'get'
  })
}

// 新增批次
export function addBatch(data) {
  return request({
    url: '/ewem/batch',
    method: 'post',
    data: data
  })
}

// 修改批次
export function updateBatch(data) {
  return request({
    url: '/ewem/batch',
    method: 'put',
    data: data
  })
}

// 删除批次
export function delBatch(id) {
  return request({
    url: '/ewem/batch/' + id,
    method: 'delete'
  })
}

// 导出批次
export function exportBatch(query) {
  return request({
    url: '/ewem/batch/export',
    method: 'get',
    params: query
  })
}