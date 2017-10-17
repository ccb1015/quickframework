/**
 * Created by Administrator on 2017/9/6.
 */
import React, {Component} from 'react';
import 'epm-ui-css/dist/css/epm-ui.css';

class CommonAdd extends Component {

    //返回树结构中给定id的对象
    treeIterator(id, tree) {
        var obj = null;
        for (let j = 0; j < tree.length; j++) {
            if (tree[j].id == id) {
                // delete tree[j].children;
                obj = tree[j];
                return obj;
            }
            if (tree[j].children != null && tree[j].children.length > 0) {
                obj = this.treeIterator(id, tree[j].children);
                if (obj != null) {
                    return obj;
                }
            }
        }
        return obj;
    }

    //关闭新增、修改编辑组件
    handleClose() {
        this.props.handleCancel();
    }

    formGetter(getter) {
        this.getValue = getter.value;
    }
}

export default CommonAdd;