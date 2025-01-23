import React,{useState} from "react"

function App() {

  const [text , setText] = useState(12);
  const change = (e) =>{
    console.log(event.target.value);

    setText(e.target.value);
  }


  return (
    <div>
      <h1>Library</h1>
      <p>Books in your library</p>
      
      <div>
      <input type="text" value={text} onChange={change} />
      </div>
      <div>
      <p>{text}</p>
      </div>
    </div>
    
  )
}

export default App
