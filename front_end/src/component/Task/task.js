import React, { useEffect, useState } from "react";
import axios from "axios";

const Task = () => {
  const [user, setUser] = useState(null);
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem("loggedInUser"));

    if (!storedUser || !storedUser.userId) {
      console.error("User not logged in!");
      return;
    }

    setUser(storedUser);
    const userId = storedUser.userId;

    // Fetch tasks for logged-in user
    axios
      .get(`http://localhost:8080/task/user/${userId}`)
      .then((res) => setTasks(res.data))
      .catch((err) => console.error("Failed to fetch tasks", err))
      .finally(() => setLoading(false));
  }, []);

  // Toggle task completion and update backend
  const toggleCompletion = async (task) => {
    const updatedTask = { ...task, compleated: !task.compleated };

    try {
      await axios.put(`http://localhost:8080/task/updat/${task.id}`, updatedTask);
      
      // Update local state only if backend call succeeds
      setTasks((prev) =>
        prev.map((t) => (t.id === task.id ? updatedTask : t))
      );
    } catch (err) {
      console.error("Failed to update task", err);
      alert("Failed to update task status. Try again.");
    }
  };

  if (loading) return <p className="mt-3">Loading tasks...</p>;

  return (
    <div className="container mt-3">
      {user && <h3>Welcome, {user.userName}</h3>}

      <h4>My Tasks</h4>
      {tasks.length === 0 ? (
        <p>No tasks assigned.</p>
      ) : (
        <ul className="list-group">
          {tasks.map((t) => (
            <li
              key={t.id}
              className="list-group-item d-flex justify-content-between align-items-center"
            >
              <div>
                <strong>{t.title}</strong> - {t.description}
              </div>
              <div>
                <input
                  type="checkbox"
                  checked={t.compleated}
                  onChange={() => toggleCompletion(t)}
                />{" "}
                {t.compleated ? "✅ Completed" : "⏳ Pending"}
              </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default Task;
