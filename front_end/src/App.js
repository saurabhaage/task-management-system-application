import logo from "./logo.svg";
import SignUp from "./component/SignUp/signUp";
import Task from "./component/Task/task";
import { Routes, Route } from "react-router-dom";
import UserList from "./component/UserList/userlist";
import UserTasks from "./component/Task/userTask";
import Home from "./component/Home/home";
import Admin from "./component/Admin/admin";
import Login from "./component/LoginIn/login";

function App() {
  return (
    <div className="App">
      {/* <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/tasks" element={<Task />} />
    </Routes> */}
      {/* <Routes>
        <Route path="/" element={<UserList />} /> Default page
        <Route path="/tasks/:userId" element={<UserTasks />} /> Show tasks for specific user
      </Routes> */}
      {/* <Home></Home> */}
        <Routes>
        {/* Home Page */}
        <Route path="/" element={<Home />} />

        {/* Login Pages */}
        <Route path="/admin-login" element={<Admin />} />
        <Route path="/user-login" element={<Login />} />

        {/* Registration Page */}
        <Route path="/register" element={<SignUp />} />

        {/* Dashboards */}
        <Route path="/admin" element={<UserList />} />   {/* Admin dashboard */}
        <Route path="/user" element={<Task />} />       {/* User dashboard showing tasks */}

        {/* Tasks for specific user */}
        <Route path="/tasks/:userId" element={<UserTasks />} />
      </Routes>
    </div>
  );
}

export default App;
