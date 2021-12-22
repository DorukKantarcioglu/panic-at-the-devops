import React from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';


export default function CovidInfoBox(props) {
  return (
    <div className=" button m-5 p-0" >
      <div className="card-body ml-10 p-0" style={{padding:"-10px"}}>
        <div className=" ">
          <div className="">
            <div
              style={{
                height: 0,
                fontSize: 20,
                fontFamily: "",
                color: "#fffff",
              }}
            >
              {props.name}
            </div>
            <div className="" style={{ fontSize: "20pt", color: "#ffff" }}>
              {parseInt(props.value) < 10 ? "0" + props.value : props.value}
            </div>
          </div>
           
          <div className="d-flex flex-column col-4 font-size py-2 content-wrap  ">
  
            </div>
          </div>
        </div>
      </div>
  );
}
