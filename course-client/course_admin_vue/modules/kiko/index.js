import KikoToolTip from './packages/tips/index.js'

const install = function(Vue) {
  Vue.component(KikoToolTip.name, KikoToolTip)
  Vue.prototype.$kiko_tooltip = KikoToolTip.installToolTip
}
export default install
