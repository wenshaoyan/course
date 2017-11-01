/**
 * Created by yanshaowen on 2017/11/1
 * 数组相关的方法
 */
'use strict';

class ArrayUtil {
    /**
     * json数组排序
     * @param filed             排序字段
     * @param rev               true:顺序 false:倒序
     * @param primer            对排序字段对应的值 执行对应的函数
     * @return {Function}
     * 例如: let arr = [{a:11},{a:2}]   arr.sort(jsonArraySort('a',true,parseInt))   结果:arr为[{a:2},{a:11}]
     */
    static sort(filed, rev, primer) {
        rev = (rev) ? -1 : 1;
        return function (a, b) {
            a = a[filed];
            b = b[filed];
            if (typeof (primer) != 'undefined') {
                a = primer(a);
                b = primer(b);
            }
            if (a < b) {
                return rev * -1;
            }
            if (a > b) {
                return rev * 1;
            }
            return 1;
        }
    }

    /**
     * 找出json数组指定字段的最大值
     * @param list              json数组
     * @param filed             指定字段
     * @param primer            对排序字段对应的值 执行对应的函数
     * @param type              min:最小值  max:最大值
     * @return{number}          对应元素的索引
     * 例如: let arr = [{a:11},{a:2}]   jsonArrayFindItem(arr,'a',parseInt,'min'))   结果:1
     */
    static findItem(list, filed, primer, type) {
        if (list instanceof Array && typeof filed === 'string' && filed.length > 0 && list.length > 0) {
            let itemIndex = 0;
            let itemV = 0;
            list.forEach((value, index) => {
                let tmp = value[filed];
                if (typeof (primer) !== 'undefined') {
                    tmp = primer(value[filed])
                }
                if (index === 0) {
                    itemIndex = index;
                    itemV = tmp
                } else {
                    if (itemV < tmp) {
                        itemV = tmp;
                        itemIndex = index;
                    }
                }
            });
            return itemIndex;
        }
        return -1;
    }

    /**
     * json数组指定的字段添加前缀和后缀
     * @param list          json数组
     * @param filed         字段名称
     * @param prefix        增加的前缀
     * @param suffix        增加的后缀
     */
    static valueJoin(list, filed, prefix, suffix) {
        if (typeof prefix !== 'string') prefix = '';
        if (typeof suffix !== 'string') suffix = '';
        list.forEach(value => {
            if (filed in value && typeof value[filed] === 'string') value[filed] = prefix + value[filed] + suffix;
        })

    }

    /**
     * 按数组下标交换元素
     * @param list
     * @param i
     * @param j
     */
    static changeBySub(list, i, j) {
        const temp = list[i];
        list[i] = list[j];
        list[j] = temp;
        /*list[i] = list[i] ^ list[j];
        list[j] = list[j] ^ list[i];
        list[i] = list[i] ^ list[j];*/
    }


}

module.exports = ArrayUtil;