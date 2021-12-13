import React from "react";

export default function FormInput(props) {
  return (
    <div className={`form-group ${props.col ? "col-" + props.col : ""}`}>
      <label htmlFor={props.name} className="form-label">
        {props.text}
      </label>

      <input
        disabled={props.disabled}
        type={props.type}
        className="form-control"
        name={props.name}
        value={props.value}
        onChange={props.onChange}
        onSelect={props.onSelect}
        checked={props.checked}
        required={props.required ? props.required : false}
        min={props.min}
      />
    </div>
  );
}
