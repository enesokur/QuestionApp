import { BrowserRouter, Routes, Route, Navigate} from 'react-router-dom';
import Navbar from "./components/Navbar/Navbar.js";
import Home from "./components/Home/Home.js";
import User from "./components/User/User.js";
import Auth from './components/Auth/Auth.js';
function App() {
  return (
    <div>
      <BrowserRouter>
        <Navbar></Navbar>
        <Routes>
          <Route path="/" element={
            localStorage.getItem("userId") != null ? <Home/> : <Navigate to="/auth"/>
          }></Route>
          <Route path="/users/:userId" element={<User/>}></Route>
          <Route path="/auth" element={
            localStorage.getItem("userId") != null ? <Navigate to="/"/> : <Auth/>
          }></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
