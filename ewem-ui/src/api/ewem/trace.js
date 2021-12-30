import request from '@/utils/request'

// 查询码详细
export function getTrace(code) {
  return request({
    url: '/trace/' + code,
    method: 'get'
  })
}

// 防伪验证
export function antiCheck(code, antiCode) {
  return request({
    url: '/trace/anti/' + code + '?antiCode=' + antiCode,
    method: 'get'
  })
}
