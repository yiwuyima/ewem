import request from '@/utils/request'

// 查询扫码记录列表
export function listScanLog(query) {
  return request({
    url: '/ewem/scanLog/list',
    method: 'get',
    params: query
  })
}

// 导出扫码记录
export function exportScanLog(query) {
  return request({
    url: '/ewem/scanLog/export',
    method: 'get',
    params: query
  })
}
