import { initializeApp } from 'firebase/app';
import {
  getAuth,
  signInWithRedirect,
  GoogleAuthProvider,
  signInWithPopup,
} from 'firebase/auth';

const firebaseConfig = {
  apiKey: 'AIzaSyDS-hD-uILFTmM19T2IszHLGDAYYNw4_tE',
  authDomain: 'e-commence-db-4fd4e.firebaseapp.com',
  projectId: 'e-commence-db-4fd4e',
  storageBucket: 'e-commence-db-4fd4e.appspot.com',
  messagingSenderId: '147327862906',
  appId: '1:147327862906:web:17fb6f37d673d381bc13c3',
};

// Initialize Firebase
const firebaseApp = initializeApp(firebaseConfig);

const provider = new GoogleAuthProvider();

provider.setCustomParameters({
  prompt: 'select_account',
});

export const auth = getAuth();
export const signInWitGooglehPopup = () => signInWithPopup(auth, provider);
