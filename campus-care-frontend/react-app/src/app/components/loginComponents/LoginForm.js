import { Form, Button, Card, App, Modal } from "react-bootstrap";
import { useState } from "react";
import { useHistory } from "react-router-dom";
import React from "react";
import LocalStorageService from "../../../service/LocalStorageService";
import axios from "axios";
import Dashboard from "../../Dashboard";
import ForgotPasswordForm from "./ForgotPasswordForm";

export default function LoginForm(props) {
  const [credentials, setCredentials] = useState({});
  const history = useHistory();

  const handleChange = (event) => {
    setCredentials({
      ...credentials,
      [event.target.name]: event.target.value,
    });
  };

  const [show, setShow] = useState(false);
  const [askEmail, setEmail] = useState(true);
  const handleClose = () => {
    setShow(false);
    setEmail(true);
  };

  const handleShow = () => setShow(true);

  const signIn = async () => {
    let path = `home`;
    history.push(path);
  };

  const handleEmail = () => {
    setEmail(false);
  };
  return (
    <Form>
      <div>
        <label
          className="display-6 m-2 justify-content-center"
          style={{ alignContent: "auto", color: "GrayText" }}
        >
          LOGIN
        </label>
      </div>
      <Form.Group className="mb-3" controlId="formBasicEmail">
        <Form.Label>Id</Form.Label>
        <Form.Control
          type="id"
          placeholder="Enter your id"
          name="id"
          //onChange={handleChange()}
        />
      </Form.Group>

      <Form.Group className="mb-3" controlId="formBasicPassword" hidden={true}>
        <Form.Label>Password</Form.Label>
        <Form.Control
          type="password"
          placeholder="Password"
          name="password"
          //onChange={handleChange()}
        />
      </Form.Group>
      <Form.Group className="mb-3" controlId="formBasicCheckbox">
        <Form.Check type="checkbox" label="Check me out" />
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
            onClick={handleShow}
          >
            Forgot Password
          </Card>
          <Modal show={show} onHide={handleClose}>
            <Modal.Header closeButton>
              <Modal.Title>Change Password</Modal.Title>
            </Modal.Header>
            <Modal.Body>
              {" "}
              <label for="e-mail" class="col-form-label" hidden={!askEmail}>
                E-mail:
              </label>
              <input
                type="text"
                class="form-control"
                id="e-mail"
                hidden={!askEmail}
              ></input>
              <label
                for="verificationCode"
                class="col-form-label"
                hidden={askEmail}
              >
                VerificationCode:
              </label>
              <input
                type="text"
                class="form-control"
                id="verificationCode"
                hidden={askEmail}
              ></input>
            </Modal.Body>
            <Modal.Footer>
              <Button variant="secondary" onClick={handleClose}>
                Close
              </Button>
              <div>
                {askEmail ? (
                  <Button onClick={handleEmail}> Send Email</Button>
                ) : (
                  <Button onClick={handleClose}>Verify Code</Button>
                )}
              </div>
            </Modal.Footer>
          </Modal>
        </div>
      </div>
    </Form>
  );
}
