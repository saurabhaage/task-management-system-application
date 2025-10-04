import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const Admin = () => {
  const [user, setUser] = useState({ userEmail: "", userPassword: "" });
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const onInputChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const onSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);

    try {
      const res = await axios.post("http://localhost:8080/user/login", user);
      const loggedInUser = res.data;

      // Check if the role is ADMIN
      if (loggedInUser.role !== "ADMIN") {
        alert("Access Denied! Only admins can login here.");
        setLoading(false);
        return;
      }

      // Save admin info + token in localStorage
      localStorage.setItem("loggedInAdmin", JSON.stringify(loggedInUser));

      alert("Admin Login Successful!");
      navigate("/admin"); // Redirect to admin dashboard
    } catch (err) {
      console.error(err);
      alert("Login Failed! Please check your email or password.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6 border rounded p-4 shadow">
          <h2 className="mb-3 text-center">ADMIN Login</h2>
          <form onSubmit={onSubmit}>
            <div className="mb-3">
              <label>Email:</label>
              <input
                type="email"
                name="userEmail"
                value={user.userEmail}
                onChange={onInputChange}
                className="form-control"
                autoComplete="username"
                placeholder="Enter admin email"
                required
              />
            </div>
            <div className="mb-3">
              <label>Password:</label>
              <input
                type="password"
                name="userPassword"
                value={user.userPassword}
                onChange={onInputChange}
                className="form-control"
                autoComplete="current-password"
                placeholder="Enter password"
                required
              />
            </div>
            <button type="submit" className="btn btn-success w-100 mb-2">
              {loading ? "Logging in..." : "Login"}
            </button>
          </form>

          {/* Register Button */}
          <div className="text-center mt-2">
            <p>
              New Admin?{" "}
              <button
                className="btn btn-link"
                onClick={() => navigate("/register")}
              >
                Register Here
              </button>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Admin;
