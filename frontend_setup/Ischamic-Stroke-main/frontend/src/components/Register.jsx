import React, { useState } from "react";
import API from "../services/api";

function Register() {

  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleRegister = async () => {
    try {
      const res = await API.post("/auth/register", {
        username: username,
        email: email,
        password: password
      });

      alert(res.data);

    } catch (err) {
      console.error(err);
      alert("Registration Failed ❌");
    }
  };

  return (
    <div style={{ textAlign: "center", marginTop: "100px" }}>
      
      <h2>Register</h2>

      <input
        name="username"
        placeholder="Enter Username"
        onChange={(e) => setUsername(e.target.value)}
      /><br /><br />

      <input
        name="email"
        type="email"
        placeholder="Enter Email"
        onChange={(e) => setEmail(e.target.value)}
      /><br /><br />

      <input
        name="password"
        type="password"
        placeholder="Enter Password"
        onChange={(e) => setPassword(e.target.value)}
      /><br /><br />

      <button onClick={handleRegister}>Register</button>

    </div>
  );
}

export default Register;