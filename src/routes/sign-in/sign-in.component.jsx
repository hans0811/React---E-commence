import React from 'react';
import { signInWitGooglehPopup } from '../../utils/firebase/firebase.utils';

const SignIn = () => {
  const logGoogleUser = async () => {
    const response = await signInWitGooglehPopup();
    console.log(response);
  };

  return (
    <div>
      <h1>Sing In page</h1>
      <button onClick={logGoogleUser}>Sign in with Google Popup</button>
    </div>
  );
};

export default SignIn;
