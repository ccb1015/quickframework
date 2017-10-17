/**
 * Created by Administrator on 2017/9/6.
 */
import React, {Component} from 'react';
import 'epm-ui-css/dist/css/epm-ui.css';

class CommonSearchBox extends Component {
    handleSubmit() {
        this.props.receiveSearchData(this.state);
    }
}

export default CommonSearchBox;