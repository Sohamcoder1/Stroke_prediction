import React, { useState } from "react";
import axios from "axios";

function UploadForm() {
  const [name, setName] = useState("");
  const [age, setAge] = useState("");
  const [gender, setGender] = useState("");
  const [file, setFile] = useState(null);
  const [result, setResult] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!file) {
      alert("Please select a file");
      return;
    }

    const formData = new FormData();
    formData.append("name", name);
    formData.append("age", age);
    formData.append("gender", gender);
    formData.append("file", file);

    try {
      const response = await axios.post(
        "http://localhost:8080/api/upload",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );

      console.log(response.data);
      setResult(response.data);
    } catch (error) {
      console.error(error);
      alert("Upload failed!");
    }
  };

  return (
    <div style={{ textAlign: "center", marginTop: "30px" }}>
      <h2>Stroke Detection Upload</h2>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Enter Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <br /><br />

        <input
          type="number"
          placeholder="Enter Age"
          value={age}
          onChange={(e) => setAge(e.target.value)}
        />
        <br /><br />

        <input
          type="text"
          placeholder="Enter Gender"
          value={gender}
          onChange={(e) => setGender(e.target.value)}
        />
        <br /><br />

        <input
          type="file"
          accept="image/*"
          onChange={(e) => setFile(e.target.files[0])}
        />
        <br /><br />

        <button type="submit">Upload</button>
      </form>

      {/* ✅ Show Result */}
      {result && (
        <div style={{ marginTop: "20px" }}>
          <h3>Result:</h3>
          <p><b>Name:</b> {result.name}</p>
          <p><b>Prediction:</b> {result.prediction}</p>
          <p><b>Confidence:</b> {result.confidence}</p>
          <p><b>File Path:</b> {result.filePath}</p>
        </div>
      )}
    </div>
  );
}

export default UploadForm;