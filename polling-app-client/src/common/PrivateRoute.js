import React from 'react';
import {
    Route,
    Redirect
  } from "react-router-dom";
import {notification } from 'antd';
  
const PrivateRoute = ({ component: Component, authenticated,roles, ...rest }) => (
    
  
<Route
      {...rest}
      
      render={props =>
        {
        console.log(authenticated)
        
        if (authenticated) 
        {
          return  <Component {...rest} {...props} />
        } 
        else
        {
          notification.error({
            message: 'Polling App',
            description:'Sorry! protected route'
          })   
           return <Redirect
            to={{
              pathname: '/login',
              state: { from: props.location }
            }}
          />
          }
      }
      }
    />
);
  
export default PrivateRoute