import { Link } from "react-router-dom";
import { Button } from "react-bootstrap";

const MenuTab = (props) => {
  const logout = () => {
    localStorage.clear();
    // you can also like localStorage.removeItem('Token');
    window.location.href = "/login";
  };
  return (
    <div className="d-flex justify-content-between">
      <ul className="menu" style={{ padding: "10 px", marginTop: 10 }}>
        <li className="menu">
          {" "}
          <Link to="/home"> Home </Link>{" "}
        </li>
        <li className="menu">
          {" "}
          <Link to="/appointment"> Appointment </Link>{" "}
        </li>
        <li className="menu">
          {" "}
          <Link to="/campusmap"> Campus Map </Link>{" "}
        </li>
        <li className="menu">
          {" "}
          <Link to="/notifications"> Notifications </Link>{" "}
        </li>
        <li className="menu">
          {" "}
          <Link to="/profile"> Profile </Link>{" "}
        </li>

        <li className="menu">
          {" "}
          <button
            className="button"
            style={{ marginTop: "4px", marginLeft: "600px" }}
            onClick={logout}
          >
            {" "}
            Logout{" "}
          </button>{" "}
        </li>
      </ul>
    </div>
  );
};

export default MenuTab;
