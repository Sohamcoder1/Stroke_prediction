import React, { useState } from "react";
import UploadForm from "../components/UploadForm";
import ResultCard from "../components/ResultCard";
import Loader from "../components/Loader";
import Login from "../components/Login";

function Home() {
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);
  const [user, setUser] = useState(null);

  return (
    <div className="container">
      
      <div className="card">

        <h1 className="title">🧠 Stroke Detection AI</h1>

        {!user ? (
          <Login setUser={setUser} />
        ) : (
          <>
            <p className="welcome">Welcome, {user.name} 👋</p>

            <UploadForm setResult={setResult} setLoading={setLoading} />

            {loading && <Loader />}

            <ResultCard result={result} />
          </>
        )}

      </div>
    </div>
  );
}

export default Home;