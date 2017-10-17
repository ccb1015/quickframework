/**
 * Created by Administrator on 2017/9/4.
 */
import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import logo from './image/logo.svg';
import './style/index.css';

class App extends Component {
    render() {
        return (
            <div className="App">
                <div className="App-header">
                    <img src={logo} className="App-logo" alt="logo" />
                    <h2>Welcome to React</h2>
                </div>
                <p className="App-intro">
                    To get started, edit <code>src/App.js</code> and save to reload.
                </p>
            </div>
        );
    }
}

export default App;
ReactDOM.render(<App />, document.getElementById('root'));
