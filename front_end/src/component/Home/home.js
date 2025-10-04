import React from "react";
import { useNavigate } from "react-router-dom";
import "./home.css";

const HomePage = () => {
  const navigate = useNavigate();

  return (
    <div className="home-container">
      <h1 className="text-center mb-5">Welcome to Task Management System</h1>
      <div className="login-sections">
        {/* Admin Login Section */}
        <div className="login-card admin-section">
          <h2>Admin Login</h2>
          <p>Login to manage users and tasks</p>
          <button
            className="btn btn-danger w-100"
            onClick={() => navigate("/admin-login")}
          >
            Admin Login
          </button>
        </div>

        {/* User Login Section */}
        <div className="login-card user-section">
          <h2>User Login</h2>
          <p>Login to view and manage your tasks</p>
          <button
            className="btn btn-primary w-100"
            onClick={() => navigate("/user-login")}
          >
            User Login
          </button>
        </div>
      </div>
    </div>
  );
};

export default HomePage;
