import React, { Component } from 'react';
import ReactDOM from "react-dom";
import { Steps , Divider} from 'antd';
// import 'antd/dist/antd.css';
import 'antd/dist/reset.css';
import LoadingIndicator  from '../../common/LoadingIndicator';
import './Profile.css';
import NotFound from '../../common/NotFound';
import ServerError from '../../common/ServerError';
import trophy from "../../trophy.jpg"
import {
    LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend,PieChart, Pie, Cell,AreaChart,linearGradient,Area
  } from 'recharts';
import { getRanking,getUserProfile } from '../../util/APIUtils';

  const data = [
    {
      "name": "Page A",
      "uv": 4000,
      "pv": 2400,
    },
    {
      "name": "Page B",
      "uv": 3000,
      "pv": 1398,
    },
    {
      "name": "Page C",
      "uv": 2000,
      "pv": 9800,
    },
    {
      "name": "Page D",
      "uv": 2780,
      "pv": 3908,
    },
    {
      "name": "Page E",
      "uv": 1890,
      "pv": 4800,
    },
    {
      "name": "Page F",
      "uv": 2390,
      "pv": 3800,
    },
    {
      "name": "Page G",
      "uv": 3490,
      "pv": 4300,
    }
  ]
  

const Step = Steps.Step;



class Ranking extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            current: 0,
            data:null,
            isLoading:true
          };


    this.setCurrentValue = this.setCurrentValue.bind(this);
    this.setCurrentValue(1)
        
    }

    loadUserProfile(username) {
        this.setState({
            isLoading: true
        });

        getUserProfile(username)
        .then(response => {
            this.setState({
                user: response,
               
            });
        }).catch(error => {
            if(error.status === 404) {
                this.setState({
                    notFound: true,
                   
                });
            } else {
                this.setState({
                    serverError: true,
                   
                });        
            }
        });        
    }
      
    componentDidMount() {
        const username = this.props.username;
        this.loadUserProfile(username);
        getRanking(username)
        .then(response => {
        console.log(response)
            this.setState({
            data:response.votesGrouped
        });
        }).catch(error => {
       
        }).finally(()=>{
            this.setState({
                isLoading: false
            });  
        });
        this.setState({loaded: true});
        console.log(this.state.data)
    }

    componentDidUpdate(nextProps) {
        if(this.props.username !== nextProps.username) {
            this.loadUserProfile(nextProps.username);
        }        
    }

    setCurrentValue(current)
    {
        console.log('onChange:', current);
        this.setState({ current });
    }

    render() {
        
        const { current } = this.state;
        console.log(current)
        
        if(this.state.isLoading) {
            return <LoadingIndicator />;
        }

        if(this.state.notFound) {
            return <NotFound />;
        }

        if(this.state.serverError) {
            return <ServerError />;
        }

        

        return (
            <div>
            <img src={trophy} alt="poll" className="trophy" />
            <Divider />
    
            <Steps current={current} onChange={this.setCurrentValue} direction="vertical">
              <Step title="Step 1" description="This is a description." />
              <Step title="Step 2" description="This is a description." />
              <Step title="Step 3" description="This is a description." />
            </Steps>

            <Divider />
            <h1>Statics</h1>
            <AreaChart width={730} height={250} data={this.state.data}
                margin={{ top: 10, right: 30, left: 0, bottom: 0 }}>
                <defs>
                    <linearGradient id="colorUv" x1="0" y1="0" x2="0" y2="1">
                    <stop offset="5%" stopColor="#8884d8" stopOpacity={0.8}/>
                    <stop offset="95%" stopColor="#8884d8" stopOpacity={0}/>
                    </linearGradient>
                    <linearGradient id="colorPv" x1="0" y1="0" x2="0" y2="1">
                    <stop offset="5%" stopColor="#82ca9d" stopOpacity={0.8}/>
                    <stop offset="95%" stopColor="#82ca9d" stopOpacity={0}/>
                    </linearGradient>
                </defs>
                <XAxis dataKey="date" />
                <YAxis />
                <CartesianGrid strokeDasharray="3 3" />
                <Tooltip />
                {/* <Area type="monotone" dataKey="uv" stroke="#8884d8" fillOpacity={1} fill="url(#colorUv)" /> */}
                <Area type="monotone" dataKey="count" stroke="#82ca9d" fillOpacity={1} fill="url(#colorPv)" />
            </AreaChart>
          </div>
        );
    }
}
export default Ranking;