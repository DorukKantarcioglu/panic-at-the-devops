
import {Link} from "react-router-dom";


const MenuTab = (prop) => {


    return (
            <ul className="menu"  style={{padding: "10 px", marginTop: 10}}>
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
                    <Link to="/logout"> Logout </Link>{" "}
                </li>
            </ul>

    );
};

export default MenuTab;
