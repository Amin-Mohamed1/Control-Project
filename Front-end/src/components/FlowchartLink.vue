<template>
  <g @mouseover="handleMouseOver"
    @mouseleave="handleMouseLeave">
    <!-- <defs>
      <path id="textPath" :d="dAttr"></path>
    </defs> -->
    <path :d="dAttr" :style="pathStyle"></path>
    <a v-if="show.delete" @click="deleteLink">
      <text 
        text-anchor="middle" 
        :transform="arrowTransform + 'translate(' + dy + ', ' + dx + ')'"
        font-size="22">&times; </text>
    </a>
    
    <path v-else d="M -1 -1 L 0 1 L 1 -1 z"
      :style="arrowStyle"
      :transform="arrowTransform + 'translate(' + dy + ', ' + dx + ')'"
      ></path>
      
    <a>
      <text 
        
        
        :transform="arrowTransform + 'translate(' + (dy+17) + ', ' + (dx-10) + ') rotate(' + angle + ')'"
        font-size="22"> {{weight}} 
        
      </text>
    </a>
  </g>
</template>

<script>
export default {
  name: 'FlowchartLink',
  props: {
    // start point position [x, y]
    start: {
      type: Array,
      default() {
        return [0, 0]
      }
    },
    // end point position [x, y]
    end: {
      type: Array,
      default() {
        return [0, 0]
      }
    },
    id: Number,
    weight: Number,
  },
  data() {
    return {
      rx: 0,
      show: {
        delete: false,
      }
      
    }
  },
  methods: {
    handleMouseOver() {
      if (this.id) {
        this.show.delete = true;
      }
    },
    handleMouseLeave() {
      this.show.delete = false;
    },
    caculateCenterPoint() {
      // caculate arrow position: the center point between start and end
      const dx = (this.end[0] - this.start[0]) / 2;
      const dy = (this.end[1] - this.start[1]) / 2;
      return [this.start[0] + dx, this.start[1] + dy];
    },
    caculateRotation() {
      // caculate arrow rotation
      const angle = -Math.atan2(this.end[0] - this.start[0], this.end[1] - this.start[1]);
      const degree = angle * 180 / Math.PI;
      return degree < 0 ? degree + 360 : degree;
    },
    deleteLink() {
      this.$emit('deleteLink')
    }
  },
  computed: {
    angle() {
      if(this.end[0] - this.start[0] > 0) return 90;
      return 270;
    },
    dx() {
      return 5;
  
    },
    dy() {
      if(this.end[0] - this.start[0] > 0) return 0;
      return Math.max((this.end[0] - this.start[0])/ 2 , -250 );
    },
    pathStyle() {
      return {
        stroke: 'rgb(255, 136, 85)',
        strokeWidth: 2.73205,
        fill: 'none',
      }
    },
    arrowStyle() {
      return {
        stroke: 'rgb(255, 136, 85)',
        strokeWidth: 5.73205,
        fill: 'none',
      }
    },
    arrowTransform() {
      const [arrowX, arrowY] = this.caculateCenterPoint();
      const degree = this.caculateRotation()
      return `translate(${arrowX}, ${arrowY}) rotate(${degree})`;
    },
    dAttr() {
      let cx = this.start[0], cy = this.start[1], ex = this.end[0], ey = this.end[1];
      let x1 = cx, y1 = cy + 50, x2 = ex, y2 = ey - 50;
      //return `M ${cx}, ${cy} C ${x1}, ${y1}, ${x2}, ${y2}, ${ex}, ${ey}`;
      if (ex > cx + 10){
      return `M ${cx}, ${cy} L ${ex}, ${ey}`;
      }
      this.rx = Math.abs(ex - cx) / 2;  // x radius of the ellipse
      let ry = Math.abs(ey - cy) / 2;  // y radius of the ellipse
      let xRotation = 0;  // x-axis rotation
      let largeArcFlag = 1;  // 1 means that the arc spans more than or equal to 180 degrees
      let sweepFlag = 0;  // 1 means that the arc will be drawn in a "positive-angle" direction
      return `M ${cx}, ${cy} A ${this.rx}, ${Math.min(this.rx, 250)}, ${xRotation}, ${largeArcFlag}, ${sweepFlag}, ${ex}, ${ey}`;
      
    }
  }
}
</script>

<style scoped lang="scss">
g {
  cursor: pointer;
}
</style>