import { BrowserRouter, Routes, Route } from "react-router-dom";
import LoginPage from "./pages/LoginPage"
import RegistrationPage from "./pages/RegistrationPage"
import ProfilePage from "./pages/ProfilePage"

function App() {

  return (    
    <BrowserRouter>
      <div className="App">
        <Routes>
          <Route exact path="/" element={<LoginPage />} />
          <Route exact path="/register" element={<RegistrationPage />} />
          <Route exact path="/login" element={<LoginPage />} />
          <Route exact path="/profile" element={<ProfilePage />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App
