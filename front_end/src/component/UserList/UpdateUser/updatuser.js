import React, { useState } from "react";
import axios from "axios";

const UpdateUser = ({ user, onClose, refresh }) => {
  const [updatedUser, setUpdatedUser] = useState({
    userName: user.userName,
    userEmail: user.userEmail,
    role: user.role,
  });

  const handleChange = (e) => {
    setUpdatedUser({ ...updatedUser, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .put(`http://localhost:8080/admin/update/${user.userId}`, updatedUser)
      .then(() => {
        alert("User updated successfully");
        refresh();
        onClose();
      })
      .catch((err) => {
        console.error(err);
        alert("Failed to update user");
      });
  };

  return (
    <div className="card p-3 mt-3 shadow">
      <h4>Update User: {user.userName}</h4>
      <form onSubmit={handleSubmit}>
        <div className="mb-2">
          <input
            type="text"
            name="userName"
            placeholder="Name"
            value={updatedUser.userName}
            onChange={handleChange}
            autoComplete="off"
            className="form-control"
            required
          />
        </div>
        <div className="mb-2">
          <input
            type="email"
            name="userEmail"
            placeholder="Email"
            value={updatedUser.userEmail}
            onChange={handleChange}
            autoComplete="off"
            className="form-control"
            required
          />
        </div>
        <div className="mb-2">
          <select
            name="role"
            value={updatedUser.role}
            onChange={handleChange}
            className="form-control"
          >
            <option value="USER">USER</option>
            <option value="ADMIN">ADMIN</option>
          </select>
        </div>
        <button type="submit" className="btn btn-success me-2">
          Update
        </button>
        <button type="button" className="btn btn-secondary" onClick={onClose}>
          Cancel
        </button>
      </form>
    </div>
  );
};

export default UpdateUser;
