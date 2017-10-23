/*
 * Copyright (c) 2017. The Ontario Institute for Cancer Research. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.overture.ego.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.overture.ego.model.Page;
import org.overture.ego.model.PageInfo;
import org.overture.ego.model.entity.User;
import org.overture.ego.security.ProjectCodeScoped;
import org.overture.ego.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

  /**
   * Dependencies
   */
  @Autowired
  UserService userService;


  @RequestMapping(method = RequestMethod.GET, value = "")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "List of users", response = User.class, responseContainer = "List")
      }
  )
  public @ResponseBody
  Page<User> getUsersList(
          @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = true) final String accessToken,
          PageInfo pageInfo)
  {
    return userService.listUsers(pageInfo);
  }

  @ProjectCodeScoped
  @RequestMapping(method = RequestMethod.GET, value = "/search")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "List of users", response = User.class, responseContainer = "List")
      }
  )
  public @ResponseBody
  List<User> findUsers(
      @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = true) final String accessToken,
      @RequestParam(value = "query", required = false, defaultValue = "0") String query,
      @RequestParam(value = "count", required = false, defaultValue = "10") short count) {

    return null;
  }


  @ProjectCodeScoped
  @RequestMapping(method = RequestMethod.GET, value = "/{id}")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "User Details", response = User.class)
      }
  )
  public @ResponseBody
  User getUser(
      @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = true) final String accessToken,
      @PathVariable(value = "id", required = true) String id) {
    return userService.get(id);
  }

  @ProjectCodeScoped
  @RequestMapping(method = RequestMethod.POST, value = "/{id}/groups")
  @ApiResponses(
          value = {
                  @ApiResponse(code = 200, message = "Add groups to user", response = String.class)
          }
  )
  public @ResponseBody
  String addGroupsToUser(
          @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = true) final String accessToken,
          @PathVariable(value = "id", required = true) String userId,
          @RequestBody(required = true) List<String> groupIDs) {
     userService.addUsersToGroups(userId,groupIDs);
     return "User added to : "+groupIDs.size() + " groups successfully.";
  }

  @ProjectCodeScoped
  @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
  @ApiResponses(
      value = {
          @ApiResponse(code = 200, message = "Updated user info", response = User.class)
      }
  )
  public @ResponseBody
  User updateUser(
      @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = true) final String accessToken,
      @RequestBody(required = true) User updatedUserInfo) {
    return userService.update(updatedUserInfo);
  }

  @ProjectCodeScoped
  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public void deleteUser(
      @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = true) final String accessToken,
      @PathVariable(value = "id", required = true) String userId) {
    userService.delete(userId);
  }

  @ProjectCodeScoped
  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}/groups/{groupIDs}")
  @ResponseStatus(value = HttpStatus.OK)
  public void deleteGroupFromUser(
          @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = true) final String accessToken,
          @PathVariable(value = "id", required = true) String userId,
          @PathVariable(value = "groupIDs", required = true) List<String> groupIDs) {
    userService.deleteUserFromGroup(userId,groupIDs);

  }

}
