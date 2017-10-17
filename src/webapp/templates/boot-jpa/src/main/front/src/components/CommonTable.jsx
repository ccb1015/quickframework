/**
 * Created by Administrator on 2017/9/6.
 */
import React, {Component} from 'react';
import 'epm-ui-css/dist/css/epm-ui.css';

class CommonTable extends Component {
    handleSort(dataIndex, sortFlag) {
        this.setState({
            "sortData": {
                sortAttribute: dataIndex,
                sortType: sortFlag
            }
        }, () => {
            this.props.receiveSortData(this.state.sortData);
        });

    }

    handleUpdate(rowData){
        this.props.handleUpdate(rowData);
    }
    
	handleDel(rowData) {             //删除的确定按钮所需
        this.setState({
            visible: true,
            Id:rowData.id
        })
    }
    handleDelete(Id) {
        this.props.handleDelete(this.state.Id);
        this.setState({
            visible:false
        })
    }
    
    handleClose() {
        this.setState({
            visible: false
        })
    }
}

export default CommonTable;