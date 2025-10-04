import React, { useEffect, useState } from "react";
import axios from "axios";
import CreateTask from "../Task/createTask"; 
import UpdateUser from "./UpdateUser/updatuser";
import { useNavigate } from "react-router-dom";

const UserList = () => {
  const [users, setUsers] = useState([]);
  const [selectedUser, setSelectedUser] = useState(null);
  const [showTaskForm, setShowTaskForm] = useState(false);
  const [showUpdateForm, setShowUpdateForm] = useState(false);

  const navigate = useNavigate(); // ✅ FIXED: Initialize navigate

  const fetchUsers = () => {
    axios
      .get("http://localhost:8080/admin/allUser")
      .then((res) => setUsers(res.data))
      .catch((err) => console.error("Failed to fetch users", err));
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  const handleAssignTask = (user) => {
    setSelectedUser(user);
    setShowTaskForm(true);
  };

  const handleUpdateUser = (user) => {
    setSelectedUser(user);
    setShowUpdateForm(true);
  };

  const handleDeleteUser = (userId) => {
    if (window.confirm("Are you sure you want to delete this user?")) {
      axios
        .delete(`http://localhost:8080/admin/delete/${userId}`)
        .then(() => {
          alert("User deleted successfully");
          fetchUsers();
        })
        .catch((err) => console.error(err));
    }
  };

  const handleShowTasks = (userId) => {
    navigate(`/tasks/${userId}`); // ✅ Redirect works now
  };

  const handleCloseForms = () => {
    setShowTaskForm(false);
    setShowUpdateForm(false);
    setSelectedUser(null);
  };

  return (
    <div className="container mt-3">
      <h3>User List</h3>
      <table className="table table-bordered">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {users.map((user) => (
            <tr key={user.userId}>
              <td>{user.userId}</td>
              <td>{user.userName}</td>
              <td>{user.userEmail}</td>
              <td>{user.role}</td>
              <td>
                <button className="btn btn-primary me-1" onClick={() => handleAssignTask(user)}>
                  Assign Task
                </button>
                <button className="btn btn-warning me-1" onClick={() => handleUpdateUser(user)}>
                  Update
                </button>
                <button className="btn btn-danger me-1" onClick={() => handleDeleteUser(user.userId)}>
                  Delete
                </button>
                <button className="btn btn-info me-1" onClick={() => handleShowTasks(user.userId)}>
                  Show Tasks
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {showTaskForm && selectedUser && <CreateTask user={selectedUser} onClose={handleCloseForms} />}
      {showUpdateForm && selectedUser && (
        <UpdateUser user={selectedUser} onClose={handleCloseForms} refresh={fetchUsers} />
      )}
    </div>
  );
};

export default UserList;
