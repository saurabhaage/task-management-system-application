import React, { useState } from "react";
import axios from "axios";

const CreateTask = ({ user, onClose }) => {
  const [task, setTask] = useState({ title: "", description: "" });

  const handleChange = (e) => {
    setTask({ ...task, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    axios
      .post(`http://localhost:8080/task/create/${user.userId}`, task)
      .then((res) => {
        alert("Task assigned successfully!");
        onClose();
      })
      .catch((err) => {
        console.error(err);
        alert("Failed to assign task");
      });
  };

  return (
    <div className="card p-3 mt-3 shadow">
      <h4>Assign Task to {user.userName}</h4>
      <form onSubmit={handleSubmit}>
        <div className="mb-2">
          <input
            type="text"
            name="title"
            placeholder="Task Title"
            value={task.title}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>
        <div className="mb-2">
          <textarea
            name="description"
            placeholder="Task Description"
            value={task.description}
            onChange={handleChange}
            className="form-control"
            required
          />
        </div>
        <button type="submit" className="btn btn-success me-2">
          Assign
        </button>
        <button type="button" className="btn btn-secondary" onClick={onClose}>
          Cancel
        </button>
      </form>
    </div>
  );
};

export default CreateTask;
