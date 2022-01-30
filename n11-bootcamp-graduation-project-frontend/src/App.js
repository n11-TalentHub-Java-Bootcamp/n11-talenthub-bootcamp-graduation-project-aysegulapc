import React from 'react';
import { Route, Routes } from 'react-router-dom';
import './App.css';
import UserFormPage from './component/userForm/UserFormPage';
import UserListPage from './component/users/UserListPage';
import Menu from './component/menu/Menu';

class App extends React.Component {
  
  render() {
    return (
      <div className="App">
        <Menu></Menu>
        <Routes>
          <Route path="/" element={<UserListPage></UserListPage>}></Route>
          <Route path="/user/add" element={<UserFormPage></UserFormPage>}></Route>
        </Routes>
      </div>
    );
  }
  
}

export default App;
