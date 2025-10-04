import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const SignUp = () => {
  const [user, setUser] = useState({
    userName: "",
    userEmail: "",
    userPassword: "",
    role: "USER", // default role
  });

  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const { userName, userEmail, userPassword, role } = user;

  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();

    if (!userName || !userEmail || !userPassword) {
      alert("Please fill all fields!");
      return;
    }

    setLoading(true);
    try {
      await axios.post("http://localhost:8080/user/register", user);
      alert("SignUp Successful! Please login.");
      navigate("/user-login"); // Redirect to user login page
    } catch (error) {
      console.error(error);
      alert("SignUp Failed. Try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container">
      <div className="row justify-content-center">
        <div className="col-lg-6 col-md-8 col-sm-10 border rounded p-4 mt-5 shadow">
          <h2 className="text-center mb-4">Sign Up</h2>
          <form onSubmit={onSubmit}>
            <div className="mb-3">
              <label>User Name</label>
              <input
                type="text"
                name="userName"
                value={userName}
                onChange={onInputChange}
                className="form-control"
                placeholder="Enter your name"
                required
              />
            </div>
            <div className="mb-3">
              <label>Email</label>
              <input
                type="email"
                name="userEmail"
                value={userEmail}
                onChange={onInputChange}
                className="form-control"
                placeholder="Enter email"
                required
              />
            </div>
            <div className="mb-3">
              <label>Password</label>
              <input
                type="password"
                name="userPassword"
                value={userPassword}
                onChange={onInputChange}
                className="form-control"
                placeholder="Enter password"
                required
              />
            </div>
            <div className="mb-3">
              <label>Role</label>
              <select
                name="role"
                value={role}
                onChange={onInputChange}
                className="form-control"
              >
                <option value="USER">USER</option>
                <option value="ADMIN">ADMIN</option>
              </select>
            </div>
            <button
              type="submit"
              className="btn btn-success w-100"
              disabled={loading}
            >
              {loading ? "Signing Up..." : "Sign Up"}
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default SignUp;
