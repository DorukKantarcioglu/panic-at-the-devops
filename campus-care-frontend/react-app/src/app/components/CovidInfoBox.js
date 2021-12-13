import React from "react";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

export default function CovidInfoBox(props) {
  return (
    <div className=" bg-success py-1 mt-5 col-2 rounded buttons ">
      <div className="card-body p-0 h-100  ">
        <div className="d-flex flex-row h-100 m-2 ">
          <div className="d-flex flex-column col-8 content-wrap ">
            <div
              style={{
                height: 50,
                fontSize: 20,
                fontFamily: "",
                color: "#29377e",
              }}
            >
              {props.name}
            </div>
            <div className="" style={{ fontSize: "40pt", color: "#29377e" }}>
              {parseInt(props.value) < 10 ? "0" + props.value : props.value}
            </div>
          </div>
           
          <div className="d-flex flex-column col-4 font-size py-2 content-wrap  ">
            <div className="justify-content-start">
              <i
                className="fas fa-arrow-right"
                style={{ fontSize: "20pt", color: "#29377e" }}
              ></i>
            </div>
            <div className="mb-3" style={{ color: "#29377e" }}>
              <i className={`${props.icon} fa-2x  `}></i>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
