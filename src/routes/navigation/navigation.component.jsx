import React, { Fragment } from 'react';
import { ReactComponet as CrwnLogo } from '../../assets/crown.svg';
import { Outlet, Link } from 'react-router-dom';
import './navigation.styles.scss';

const Navigation = () => {
  return (
    <Fragment>
      <div className="navigation">
        <Link calssName="logo-conatiner" to="/">
          {/* <CrwnLogo calssName="logo" /> */}
          <div>logo</div>
        </Link>

        <div className="nav-links-container">
          <Link calssName="nav-link" to="/shop">
            SHOP
          </Link>
          <Link calssName="nav-link" to="/sign-in">
            sign in
          </Link>
        </div>
      </div>
      <Outlet />
    </Fragment>
  );
};

export default Navigation;
