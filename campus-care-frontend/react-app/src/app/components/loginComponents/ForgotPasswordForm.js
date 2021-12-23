import { Form, Button, Card, App, Modal } from "react-bootstrap";
import { useEffect, useState } from "react";
import { useHistory } from "react-router-dom";
import React from "react";
import LocalStorageService from "../../../service/LocalStorageService";
import axios from "axios";
import Dashboard from "../../pages/Dashboard";
import { render } from "@testing-library/react";
export default function ForgotPasswordForm(props) {
  const [email, showEmail] = useState(true);
  const [newPasswordForm, showNewPasswordForm] = useState(false);
  const [verification, showVerification] = useState(false);

  const verifyCode = () => {
    showNewPasswordForm(true);
    showVerification(false);
  };

  const changePassword = () => {};

  const handleEmail = () => {
    showEmail(false);
    showVerification(true);
  };

  return (
    <Modal show={props.show}>
      <Modal.Header>
        <Modal.Title>Change Password</Modal.Title>
        <button
          type="button"
          className="btn-close"
          data-bs-dismiss="modal"
          onClick={props.handleClose}
        ></button>
      </Modal.Header>
      <Modal.Body>
        {" "}
        <label for="e-mail" class="col-form-label" hidden={!email}>
          E-mail:
        </label>
        <input type="text" class="form-control" id="e-mail" hidden={!email} />
        <label
          for="verificationCode"
          class="col-form-label"
          hidden={!verification}
        >
          VerificationCode:
        </label>
        <input
          type="text"
          class="form-control"
          id="verificationCode"
          hidden={!verification}
        />
        <div hidden={!newPasswordForm}>
          <div className="row d-flex">
            <label>Enter your new password</label>
          </div>
          <input
            type="password"
            className="form-control"
            id="inputPassword2"
            placeholder="Password"
          />
          <div className="row d-flex ">
            <label>Reenter your new password</label>
          </div>
          <input
            type="password"
            className="form-control"
            id="inputPassword2"
            placeholder="Password"
          />
          <Button className="mt-4" onClick={changePassword}>
            Change password
          </Button>
        </div>
      </Modal.Body>
      <Modal.Footer className="justify-content-start">
        <Button
          className=" m-2 "
          variant="secondary"
          onClick={props.handleClose}
        >
          Close
        </Button>
        <div>
          <Button onClick={handleEmail} hidden={!email}>
            {" "}
            Send Email
          </Button>
          <Button onClick={verifyCode} hidden={!verification}>
            Verify Code
          </Button>
        </div>
      </Modal.Footer>
    </Modal>
  );
}
