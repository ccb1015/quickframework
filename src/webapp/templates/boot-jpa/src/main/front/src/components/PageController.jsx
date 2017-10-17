import React, {Component} from 'react';
import {Pagination} from 'epm-ui';

class PageController extends Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(index, size) {
        this.props.receivePageData(index - 1, size);
    }

    render() {
        return (
            <Pagination index={this.props.pageData.page + 1}
                        total={this.props.pageData.total }
                        size={this.props.pageData.size }
                        showPagiJump={ true }
                        showDataSizePicker={ true }
                        align='right'
                        onChange={ this.handleChange }/>
        );
    }
}

export default PageController;