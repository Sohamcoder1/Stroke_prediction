function ResultCard({ result }) {
  if (!result) return null;

  return (
    <div style={{ border: "1px solid black", padding: "10px", marginTop: "20px" }}>
      
      <h2>Prediction Result</h2>

      <h3>
        {result.prediction === "Stroke"
          ? "⚠️ Stroke Detected"
          : "✅ No Stroke"}
      </h3>

      <p>Confidence: {(result.confidence * 100).toFixed(2)}%</p>

      {result.prediction === "Stroke" && (
        <div>
          <h4>Emergency Guidance:</h4>
          <ul>
            <li>Seek immediate medical help</li>
            <li>Golden hour critical (4.5 hrs)</li>
            <li>Do NOT give food or water</li>
          </ul>
        </div>
      )}
    </div>
  );
}

export default ResultCard;