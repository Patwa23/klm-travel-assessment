// This api will come in the next version
import { AuthConfig } from 'angular-oauth2-oidc';

export const authConfig: AuthConfig = {
  // Url of the Identity Provider
  issuer: 'http://localhost:8080/oauth/token?grant_type=password',

  // URL of the SPA to redirect the user to after login
  redirectUri: window.location.origin + '/dashboard',

  // URL of the SPA to redirect the user after silent refresh
  silentRefreshRedirectUri: window.location.origin + '/silent-refresh.html',

  // The SPA's id. The SPA is registerd with this id at the auth-server
  clientId: 'my-trusted-client',

  // set the scope for the permissions the client should request
  // The first three are defined by OIDC. The 4th is a usecase-specific one
  scope: 'openid profile email voucher',

  // silentRefreshShowIFrame: true,
  showDebugInformation: true,

  sessionChecksEnabled: false
};