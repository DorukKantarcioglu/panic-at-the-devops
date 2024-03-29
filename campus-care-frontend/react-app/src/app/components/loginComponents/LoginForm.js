import { Form, Button, Card, App, Modal } from "react-bootstrap";
import { useState } from "react";
import { useHistory } from "react-router-dom";
import React from "react";
import LocalStorageService from "../../../service/LocalStorageService";
import axios from "axios";
import Dashboard from "../../pages/Dashboard";
import ForgotPasswordForm from "./ForgotPasswordForm";
import AuthService from "../../../service/AuthService";

export default function LoginForm(props) {
  const [show, setShow] = useState(false);
  const [email, showEmail] = useState(true);
  const [newPasswordForm, showNewPasswordForm] = useState(false);
  const [verification, showVerification] = useState(false);
  const [credentials, setCredentials] = useState({});
  const history = useHistory();

  const handleChange = (event) => {
    setCredentials({
      ...credentials,
      [event.target.name]: event.target.value,
    });
  };

  const showModal = () => {
    setShow(true);
    console.log(credentials)
  };

  const hideModal = () => {
    setShow(false);
  };

  const signIn = async () => {

    console.log(credentials.id)
    console.log(credentials.password)
     const jwtToken =  await AuthService.signIn({username: credentials.id, password: credentials.password})
     LocalStorageService.setId(credentials.id);
     LocalStorageService.setToken(jwtToken);
    history.push(`home`);
  };


  return (
    <div className="d-flex justify-content-center">
      <div className="col-5 m-2">
        <Form>
          <div className="row d-flex justify-content-start">
            <label
              className="display-6 m-2 justify-content-center"
              style={{ alignContent: "auto", color: "GrayText" }}
            >
              Campus Care
            </label>
          </div>
          <Form.Group className="m-2" controlId="formBasicEmail">
            <Form.Label>ID</Form.Label>
            <Form.Control
              type="id"
              placeholder="Enter your id"
              name="id"
              onChange={e => handleChange(e)}
            />
          </Form.Group>

          <Form.Group className="m-2" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password"
              placeholder="Password"
              name="password"
              onChange={e => handleChange(e)}
              id="show_hide_password"
            />
            <small id="passwordHelpInline" className="text-muted">
            Must be 8-20 characters long.
          </small>

          </Form.Group>
          <div className="d-flex justify-content-between">
            <div className="col-6">
              <Card
                className="button p-2"
                style={{ backgroundColor: "#a3a9f5" }}
                type="submit"
                onClick={signIn}
              >
                Submit
              </Card>
            </div>
            <div className="col-6">
              <Card
                className=" button  ml-auto p-2"
                style={{ backgroundColor: "#a3a9f5" }}
                type="button"
                data-toggle="modal"
                data-target="#exampleModalCenter"
                onClick={showModal}
              >
                Forgot Password
              </Card>
              <ForgotPasswordForm show={show} handleClose={hideModal} />
            </div>
          </div>
        </Form>
      </div>
    </div>
  );
}
