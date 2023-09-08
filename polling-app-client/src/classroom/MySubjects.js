// import React, {
//     Component
// } from 'react';
// import './MySubjects.css';
// import { Card, Typography } from "@material-tailwind/react";
// import { AnimatePresence, motion } from "framer-motion/dist/framer-motion";

// const TABLE_HEAD = ["Name", "Job", "Employed", ""];
// const TABLE_ROWS = [
//     {
//         name: "John Michael",
//         job: "Manager",
//         date: "23/04/18",
//     },
//     {
//         name: "Alexa Liras",
//         job: "Developer",
//         date: "23/04/18",
//     },
//     {
//         name: "Laurent Perrier",
//         job: "Executive",
//         date: "19/09/17",
//     },
//     {
//         name: "Michael Levi",
//         job: "Developer",
//         date: "24/12/08",
//     },
//     {
//         name: "Richard Gran",
//         job: "Manager",
//         date: "04/10/21",
//     },
// ];

// class MySubjects extends Component {
//     constructor(props) {
//         super(props);
//         this.state = {
//             subjects: [], // Store the list of subjects
//             newSubject: '', // Store a new subject
//             renameSubject: '', // Store the new name for renaming a subject
//         };
//     }

//     componentDidMount() {
//         // Call the REST API to load the subjects when the component mounts
//         this.loadSubjects();
//     }

//     // Function to load subjects from the API
//     loadSubjects = () => {
//         // Make an API request here to fetch the subjects for the teacher
//         // Update the state with the fetched subjects
//         // Example API call with fetch:
//         fetch('/api/subjects')
//             .then((response) => response.json())
//             .then((data) => this.setState({ subjects: data }));
//     };
//     // Function to handle renaming a subject
//     handleRenameSubject = (oldSubjectName) => {
//         // Make an API request to rename the subject
//         // Then, call loadSubjects to update the subject list
//         // Example API call with fetch:
//         fetch(`/api/subjects/${oldSubjectName}`, {
//             method: 'PUT',
//             body: JSON.stringify({ newSubjectName: this.state.renameSubject }),
//             headers: {
//                 'Content-Type': 'application/json',
//             },
//         })
//             .then(() => {
//                 this.setState({ renameSubject: '' }); // Clear the input field
//                 this.loadSubjects(); // Update the subject list
//             });
//     };

//     render() {
//         const { subjects, newSubject, renameSubject } = this.state;

//         return (
//             <div className="MySubjects">
//                 <h1>My Subjects</h1>
//                 <ul>
//                     {subjects.map((subject) => (
//                         <li key={subject}>
//                             {subject}{' '}
//                             <button onClick={() => this.handleRenameSubject(subject)}>Rename</button>
//                         </li>
//                     ))}
//                 </ul>
//                 <div>
//                     <input
//                         type="text"
//                         placeholder="New Subject"
//                         value={newSubject}
//                         onChange={(e) => this.setState({ newSubject: e.target.value })}
//                     />
//                     <button onClick={this.handleAddSubject}>Add</button>
//                 </div>
//                 <div>
//                     <input
//                         type="text"
//                         placeholder="Rename Subject"
//                         value={renameSubject}
//                         onChange={(e) => this.setState({ renameSubject: e.target.value })}
//                     />
//                 </div>
//             </div>


//         );
//     }

// }


import React, { Component } from 'react';
import { Divider, Radio, Table } from 'antd';
import { ColumnsType } from 'antd/es/table';

interface DataType {
  key: React.Key;
  name: string;
  age: number;
  address: string;
}

const columns: ColumnsType<DataType> = [
  {
    title: 'Name',
    dataIndex: 'name',
    render: (text: string) => <a>{text}</a>,
  },
  {
    title: 'Age',
    dataIndex: 'age',
  },
  {
    title: 'Address',
    dataIndex: 'address',
  },
];

const data: DataType[] = [
  {
    key: '1',
    name: 'John Brown',
    age: 32,
    address: 'New York No. 1 Lake Park',
  },
  {
    key: '2',
    name: 'Jim Green',
    age: 42,
    address: 'London No. 1 Lake Park',
  },
  {
    key: '3',
    name: 'Joe Black',
    age: 32,
    address: 'Sydney No. 1 Lake Park',
  },
  {
    key: '4',
    name: 'Disabled User',
    age: 99,
    address: 'Sydney No. 1 Lake Park',
  },
];

// rowSelection object indicates the need for row selection
const rowSelection = {
  onChange: (selectedRowKeys: React.Key[], selectedRows: DataType[]) => {
    console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
  },
  getCheckboxProps: (record: DataType) => ({
    disabled: record.name === 'Disabled User', // Column configuration not to be checked
    name: record.name,
  }),
};

class MySubjects extends Component {
  state = {
    selectionType: 'checkbox',
  };

  handleSelectionChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    this.setState({
      selectionType: e.target.value,
    });
  };

  render() {
    const { selectionType } = this.state;

    return (
      <div>
        <Radio.Group onChange={this.handleSelectionChange} value={selectionType}>
          <Radio value="checkbox">Checkbox</Radio>
          <Radio value="radio">Radio</Radio>
        </Radio.Group>

        <Divider />

        <Table
          rowSelection={{
            type: selectionType,
            ...rowSelection,
          }}
          columns={columns}
          dataSource={data}
        />
      </div>
    );
  }
}



export default MySubjects;