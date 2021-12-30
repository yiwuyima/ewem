import request from '@/utils/request'

// 查询环节模板列表
export function listLink(query) {
  return request({
    url: '/ewem/link/list',
    method: 'get',
    params: query
  })
}

// 查询环节模板详细
export function getLink(id) {
  return request({
    url: '/ewem/link/' + id,
    method: 'get'
  })
}

// 新增环节模板
export function addLink(data) {
  return request({
    url: '/ewem/link',
    method: 'post',
    data: data
  })
}

// 修改环节模板
export function updateLink(data) {
  return request({
    url: '/ewem/link',
    method: 'put',
    data: data
  })
}

// 删除环节模板
export function delLink(id) {
  return request({
    url: '/ewem/link/' + id,
    method: 'delete'
  })
}

// 导出环节模板
export function exportLink(query) {
  return request({
    url: '/ewem/link/export',
    method: 'get',
    params: query
  })
}