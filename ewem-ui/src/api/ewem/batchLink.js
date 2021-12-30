import request from '@/utils/request'

// 查询批次环节记录列表
export function listBatchLink(query) {
  return request({
    url: '/ewem/batchLink/list',
    method: 'get',
    params: query
  })
}

// 查询批次环节记录详细
export function getBatchLink(id) {
  return request({
    url: '/ewem/batchLink/' + id,
    method: 'get'
  })
}

// 新增批次环节记录
export function addBatchLink(data) {
  return request({
    url: '/ewem/batchLink',
    method: 'post',
    data: data
  })
}

// 修改批次环节记录
export function updateBatchLink(data) {
  return request({
    url: '/ewem/batchLink',
    method: 'put',
    data: data
  })
}

// 删除批次环节记录
export function delBatchLink(id) {
  return request({
    url: '/ewem/batchLink/' + id,
    method: 'delete'
  })
}

// 导出批次环节记录
export function exportBatchLink(query) {
  return request({
    url: '/ewem/batchLink/export',
    method: 'get',
    params: query
  })
}