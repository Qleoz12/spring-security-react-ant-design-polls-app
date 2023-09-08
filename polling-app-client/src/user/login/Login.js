import React, { Component } from 'react';
import { login } from '../../util/APIUtils';
import './Login.css';
import { Link } from 'react-router-dom';
import { ACCESS_TOKEN } from '../../constants';
import { myContext } from '../../app/myContext';

import { Form, Input, Button, notification } from 'antd';
import {UserOutlined,LockOutlined} from '@ant-design/icons'
import jwtDecode from 'jwt-decode';

const FormItem = Form.Item;


class Login extends Component {
    static contextType = myContext;

    // componentDidMount() {
    //   const user = this.context
  
    //   console.log(user) // { name: 'Tania', loggedIn: true }
    // }
    
    render() {
        let value = this.context;
        console.log(value)
        const AntWrappedLoginForm = Form.create()(LoginForm)
        return (<myContext.Consumer>
                            {context => { 
                            console.log(context)

        return (
            <div className="login-container">
                <h1 className="page-title">Login</h1>
                <div className="login-content">
                    <AntWrappedLoginForm onLogin={this.props.onLogin} />
                </div>
            </div>
        )}}
        </myContext.Consumer>)
    }
}

class LoginForm extends Component {
    static contextType = myContext;

    // UNSAFE_componentDidMount() {
    //   const user = this.context
    //   const {  toggle } = this.context
    //   console.log(toggle) 
    //   console.log(user) // { name: 'Tania', loggedIn: true }
    //   console.log(this.props)
    // }

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.toggle333 = this.toggle333.bind(this);
        this.state = {
            val: { name: "Tom", city: "Pune" }
          };
    }
    

    toggle333(e){
        // const {  toggle } = this.context
        this.context.toggle(this.state.val)
        // toggle(this.state.val)
    }


    handleSubmit(event) {
        event.preventDefault();   
        this.props.form.validateFields((err, values) => {
            if (!err) {
                const loginRequest = Object.assign({}, values);
                login(loginRequest)
                .then(response => {
                    localStorage.setItem(ACCESS_TOKEN, response.accessToken);

                    const decodedToken = jwtDecode(response.accessToken);
                    localStorage.setItem('permissions', decodedToken.roles);
                    this.context.toggle(this.state.val)
                    this.context.setRoles(decodedToken.roles)
                    this.props.onLogin();
                }).catch(error => {
                    if(error.status === 401) {
                        notification.error({
                            message: 'Polling App',
                            description: 'Your Username or Password is incorrect. Please try again!'
                        });                    
                    } else {
                        notification.error({
                            message: 'Polling App',
                            description: error.message || 'Sorry! Something went wrong. Please try again!'
                        });                                            
                    }
                });
            }
        });
    }

    render() {
        let value = this.context;
        console.log(value)
        console.log(this.props)
        const { getFieldDecorator } = this.props.form;
        return (
            
            <myContext.Consumer>
                            {context => { 
                            console.log(context)
                            return( 

            <Form onSubmit={this.handleSubmit} className="login-form">
               
                            <FormItem>
                                {getFieldDecorator('usernameOrEmail', {
                                    rules: [{ required: true, message: 'Please input your username or email!' }],
                                })(
                                    <Input
                                        prefix={<UserOutlined  type="user" />}
                                        size="large"
                                        name="usernameOrEmail"
                                        placeholder="Username or Email" />
                                )}
                            </FormItem>
                            <FormItem>
                                {getFieldDecorator('password', {
                                    rules: [{ required: true, message: 'Please input your Password!' }],
                                })(
                                    <Input
                                        prefix={<LockOutlined  type="lock" />}
                                        size="large"
                                        name="password"
                                        type="password"
                                        placeholder="Password" />
                                )}
                            </FormItem>
                            <FormItem>
                                <Button type="primary" htmlType="submit" size="large" className="login-form-button">Login</Button>
                                Or <Link to="/signup">register now!</Link>
                            </FormItem>
                            
                            <button onClick={this.toggle333}>
                            Toggle
                            </button>
                            
                        </Form>
                        )}}
                        </myContext.Consumer>
             
            
        );
    }
}


export default Login;