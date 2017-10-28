/**
 * Created by wenshao on 2017/10/27.
 */
/**
 * 对json数组进行排序
 * @param filed       排序字段
 * @param rev         true 顺序 false:倒序
 * @param primer      格式化对于值的方法 有针对整形数字的parseInt 数字的Number 字符串的String
 * example:   let list = [{a:11},{a:2}]   list.sort(jsonArraySort('a',true,parseInt))  result:list = [{a:2},{a:11}]
 */
export function jsonArraySort(filed, rev, primer) {
  rev = (rev) ? -1 : 1
  return function(a, b) {
    a = a[filed]
    b = b[filed]
    if (typeof (primer) !== 'undefined') {
      a = primer(a)
      b = primer(b)
    }
    if (a < b) {
      return rev * -1
    }
    if (a > b) {
      return rev * 1
    }
    return 1
  }
}
/**
 * 查找json数组对应字段最大或最小值的下标
 * @param list
 * @param filed     查询字段
 * @param primer    格式化对于值的方法 有针对整形数字的parseInt 数字的Number 字符串的String
 * @param type      max:获取最大值下标  min:获取最小值下标
 * @return          返回数组下标
 * example:   let list = [{a:1},{a:2}]   jsonArraySort(list,'a',parseInt,'max') result:2
 */
export function jsonArrayFindItem(list, filed, primer, type) {
  if (list instanceof Array && typeof filed === 'string' && filed.length > 0 && list.length > 0) {
    let itemIndex = 0
    let itemV = 0
    const isMax = type === 'max'
    list.forEach((value, index) => {
      let tmp = value[filed]
      if (typeof (primer) !== 'undefined') {
        tmp = primer(value[filed])
      }
      if (index === 0) {
        itemIndex = index
        itemV = tmp
      } else {
        if (isMax && itemV > tmp) {
          itemV = tmp
          itemIndex = index
        }
        if (!isMax && itemV < tmp) {
          itemV = tmp
          itemIndex = index
        }
      }
    })
    return itemIndex
  }
  return null
}
