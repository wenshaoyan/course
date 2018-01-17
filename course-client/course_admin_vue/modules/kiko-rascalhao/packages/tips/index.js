import Vue from 'vue'
import ToolTip from './src/main.vue'

ToolTip.installToolTip = function(event, opt) {
  // 停止冒泡
  event.stopPropagation();

  const options = opt

  const rect = {};
  ['top', 'left'].forEach(function(property) {
    const scroll = property === 'top' ? 'scrollTop' : 'scrollLeft'
    rect[property] = event.target.getBoundingClientRect()[property] +
      document.body[scroll] +
      document.documentElement[scroll]
  });
  ['height', 'width'].forEach(function(property) {
    rect[property] = event.target.getBoundingClientRect()[property]
  });
  options.rect = rect
  const toolTip = Vue.extend(ToolTip)
  const component = new toolTip({
    data: options
  }).$mount()
  event.target.appendChild(component.$el)
    // document.querySelector('body').appendChild(component.$el)
}

export default ToolTip
