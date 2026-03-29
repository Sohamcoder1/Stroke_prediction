import React, { useState } from "react";
import API from "../services/api";

function Login() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      const response = await API.post("/auth/login", {
        email: email,
        password: password
      });

      console.log("Response:", response.data);

      if (response.data.toLowerCase().includes("success")) {
        alert("Login Success ✅");
      } else {
        alert("Login Failed ❌");
      }

    } catch (error) {
      console.error("Login error:", error);
    }
  };

  return (
    <div style={{ textAlign: "center", marginTop: "100px" }}>

      <h2>Login</h2>

      {/* ✅ FIXED INPUT */}
      <input
        id="email"
        name="email"
        type="email"
        placeholder="Enter Email"
        autoComplete="email"
        onChange={(e) => setEmail(e.target.value)}
      />
      <br /><br />

      {/* ✅ FIXED INPUT */}
      <input
        id="password"
        name="password"
        type="password"
        placeholder="Enter Password"
        autoComplete="current-password"
        onChange={(e) => setPassword(e.target.value)}
      />
      <br /><br />

      <button onClick={handleLogin}>Login</button>

    </div>
  );
}

export default Login;