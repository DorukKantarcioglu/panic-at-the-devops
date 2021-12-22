import React from "react";
import CovidInfoBox from "./components/CovidInfoBox";
import FormInput from "./components/FormInput";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { ResponsivePie } from "@nivo/pie";
import MyResponsivePie from "./components/dashboardComponents/MyResponsivePie";
import MyResponsiveLine from "./components/dashboardComponents/MyResponsiveLine";
import MotivationalQuoteBox from "./components/dashboardComponents/MotivationalQuoteBox";

function Dashboard(props) {
  // handle click event of logout button
  const handleLogout = () => {
    props.history.push("/login");
  };

  return (
    <React.Fragment>
      <div className="row justify-content-center">
        <MotivationalQuoteBox
          value={
            "Success is not final; failure is not fatal: It is the courage to continue that counts"
          }
        />
      </div>
      <div className="row justify-content-center">
        <div className="col-6" style={{ height: 550 }}>
          <label style={{ fontSize: 20 }}>Covid Statistics</label>
          <MyResponsiveLine />
        </div>
        <div className="col-6 mt-5 " style={{ height: 550 }}>
          <MyResponsivePie />
        </div>
      </div>
    </React.Fragment>
  );
}
export default Dashboard;
