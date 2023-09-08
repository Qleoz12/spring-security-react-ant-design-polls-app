import React, { Component } from 'react';
import { getPolls } from '../util/APIUtils';
import './NewPoll.css';  
import { Form, Input, Button, Select, Col, notification,Badge,Divider } from 'antd';
import { CloseOutlined } from '@ant-design/icons';
const Option = Select.Option;
const FormItem = Form.Item;
const { TextArea } = Input


class NewPollDinamic extends Component {

  constructor(props){
    super(props)
    this.state = {
      editedResponse: undefined,
      DinamicTopic: this.props.DinamicTopic
    };

    this.handleQuestionChange = this.handleQuestionChange.bind(this);
    this.handleChoiceChange = this.handleChoiceChange.bind(this);
  }
  componentDidMount() {
    const { DinamicTopic } = this.state;
    if (DinamicTopic) this.load();
  }

  componentDidUpdate(prevProps) {
    const { DinamicTopic } = this.props;
    if (DinamicTopic !== prevProps.DinamicTopic) {
      this.setState({ DinamicTopic }, this.load);
    }
  }

  handleQuestionChange(event, index) {

    const editedResponse = {...this.state.editedResponse}; 

    const questions = [...editedResponse.choices[0].questions];
    const value = event.target.value;

    questions[index].text=value

    editedResponse.choices[0].questions = questions;
    this.setState({
      editedResponse: editedResponse
  });   
   
  }

  handleChoiceChange(event, questionIndex,choiceIndex) {
    const editedResponse = {...this.state.editedResponse}; 
    const value = event.target.value;
    editedResponse.choices[0].questions[questionIndex].options[choiceIndex] = value;
    
    this.setState({
      editedResponse: editedResponse
  });   
}

handlePollAnswerChange(event, questionIndex) {
  const editedResponse = {...this.state.editedResponse}; 
    const value = event;
    editedResponse.choices[0].questions[questionIndex].answer = editedResponse.choices[0].questions[questionIndex].options[+value];
    
    this.setState({
      editedResponse: editedResponse
  });
}

  load() {
    const { DinamicTopic } = this.state;
      getPolls(DinamicTopic)
      .then(response => {
        this.setState({
          editedResponse: response
        });
      }).catch(error => {
        this.setState({
          isLoading: false
        });  
      });
    }

    render() {
        return (
        <div>
        {this.state.editedResponse? 
        (this.state.editedResponse.choices[0].questions.map((question, questionIndex) => (
            <div key={questionIndex}>
              <h4>Question {questionIndex}</h4>
              <TextArea 
                            placeholder="Enter your question"
                            style = {{ fontSize: '16px' }} 
                            autosize={{ minRows: 3, maxRows: 6 }} 
                            name = "question"
                            value = {question.text}
                            onChange = { (event) => this.handleQuestionChange(event, questionIndex) } />
              {
                question.options.map((choice, i) =>{      
                   return <PollChoice key={i} choice={choice} choiceNumber={i} removeChoice={this.removeChoice} handleChoiceChange={  (event) =>  this.handleChoiceChange(event,questionIndex,i)}/>
                })
              }
              <FormItem className="poll-form-row">
                  <span style = {{ marginRight: '18px' }}>
                          <Select 
                              name="days"
                              defaultValue="1" 
                              onChange={ (event) =>  this.handlePollAnswerChange(event,questionIndex) }
                              value = {question.answer}
                              // value={this.state.pollLength.days}
                              style={{ width: 60 }} >
                              {
                                  question.options.filter(x=> x.length>0).map((element,i) => 
                                      <Option key={i}>{element}</Option>                                        
                                  )
                              }
                          </Select> &nbsp;Answer
                  </span>
              </FormItem>
               
              <label>
                Answer:
                <input
                  type="text"
                  value={question.answer}
                  // onChange={(event) => handleQuestionChange(event, choiceIndex, questionIndex, 'answer')}
                />
              </label>
            </div>
        ))) 
        : <p>No data available</p> }
        </div>
    )}
}

function PollChoice(props) {
  return (
      <FormItem validateStatus={props.choice.validateStatus}
      help={props.choice.errorMsg} className="poll-form-row">
          <Input 
              placeholder = {'Choice ' + (props.choiceNumber + 1)}
              size="large"
              value={props.choice} 
              className={ props.choiceNumber > 1 ? "optional-choice": null}
              onChange={(event) => props.handleChoiceChange(event, props.choiceNumber)} />

          {
              props.choiceNumber > 1 ? (
                <CloseOutlined 
                  className="dynamic-delete-button"
                  type="close"
                  disabled={props.choiceNumber <= 1}
                  onClick={() => props.removeChoice(props.choiceNumber)}
              />
               ): null
          }    
      </FormItem>
  );
}


export default NewPollDinamic;